package gamma.service.impl;

import gamma.Repository.*;
import gamma.model.entity.*;
import gamma.model.service.*;
import gamma.service.AccountService;
import gamma.service.ContragentService;
import gamma.service.LedgerEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
public class LedgerEntityServiceImpl implements LedgerEntityService {

    private final AccountService accountService;
    private final ContragentService contragentService;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final ContragentRepository contragentRepository;
    private final LedgerEntityRepository ledgerEntityRepository;
    private final DebitRepository debitRepository;
    private final CreditRepository creditRepository;
    private final DescriptionRepository descriptionRepository;
    private final ModelMapper modelMapper;

    public LedgerEntityServiceImpl(AccountService accountService, ContragentService contragentService, AccountRepository accountRepository, UserRepository userRepository, ContragentRepository contragentRepository, LedgerEntityRepository ledgerEntityRepository, DebitRepository debitRepository, CreditRepository creditRepository, DescriptionRepository descriptionRepository, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.contragentService = contragentService;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.contragentRepository = contragentRepository;
        this.ledgerEntityRepository = ledgerEntityRepository;
        this.debitRepository = debitRepository;
        this.creditRepository = creditRepository;
        this.descriptionRepository = descriptionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LedgerEntityAddServiceModel postLedgerEntity(LedgerEntityAddServiceModel ledgerEntityAddServiceModel, boolean isInvoice) {

        LedgerEntityyServiceModel ledgerEntity = new LedgerEntityyServiceModel();
        ledgerEntity.setPostingDate(ledgerEntityAddServiceModel.getPostingDate());
        ledgerEntity.setDocumentType(ledgerEntityAddServiceModel.getDocumentType());
        ledgerEntity.setDocumentNumber(ledgerEntityAddServiceModel.getDocumentNumber());
        List<Contragent> allCon = this.contragentRepository.findAll();
        BigDecimal baseDebit = new BigDecimal("0");
        BigDecimal baseCredit = new BigDecimal("0");
        for (int z = 0; z < allCon.size(); z++) {
            Contragent contragent = allCon.get(z);
            String contragents = ledgerEntityAddServiceModel.getContragents();
            String name = allCon.get(z).getName();
            baseDebit = contragent.getDebit();
            baseCredit = contragent.getCredit();
            if (contragents.equals(name)) {
                ledgerEntity.setContragent(allCon.get(z));
                contragent.setDebit(contragent.getDebit().add(ledgerEntityAddServiceModel.getDebit().get(0)));
                contragent.setCredit(contragent.getCredit().add(ledgerEntityAddServiceModel.getCredit().get(0)));
                this.contragentRepository.saveAndFlush(contragent);
            }
        }
        List<Debit> debits = new LinkedList<>();
        List<BigDecimal>  importDebits = ledgerEntityAddServiceModel.getDebit();
        for (int i = 0; i < importDebits.size(); i++) {
            Debit debit = new Debit(importDebits.get(i));
            this.debitRepository.saveAndFlush(debit);
            debits.add(debit);
        }
        ledgerEntity.setDebit(debits);
        List<Credit> credits = new LinkedList<>();
        List<BigDecimal>  importCredits = ledgerEntityAddServiceModel.getCredit();
        for (int i = 0; i < importCredits.size(); i++) {
            Credit credit = new Credit(importCredits.get(i));
            credits.add(credit);
            this.creditRepository.saveAndFlush(credit);
        }
        ledgerEntity.setCredit(credits);
        BigDecimal bigDecimal = new BigDecimal("0");
        if (ledgerEntity.getContragent().getType().equals("customer")){
            for (Credit credit : credits) {
                bigDecimal =  bigDecimal.add(credit.getCredit());
            }
            for (Debit debit : debits) {
            bigDecimal = bigDecimal.subtract(debit.getDebit());
        }
        }else {
            for (Debit debit : debits) {
                bigDecimal = bigDecimal.add(debit.getDebit());
            }
            for (Credit credit : credits) {
                bigDecimal = bigDecimal.subtract(credit.getCredit());
            }
        }
        List<String> accName = ledgerEntityAddServiceModel.getAccounts();
        List<Account> accounts = new LinkedList<>();
        List<Account> all = this.accountRepository.findAll();
        for (int i = 0; i < accName.size(); i++) {
            for (int z = 0; z < all.size(); z++) {
                AccountServiceModel accountServiceModel=new AccountServiceModel();
                if (accName.get(i).equals(all.get(z).getName())) {
                    accounts.add(all.get(z));
                    all.get(z).setDebit(all.get(z).getDebit().add(ledgerEntityAddServiceModel.getDebit().get(i)));
                    all.get(z).setCredit(all.get(z).getCredit().add(ledgerEntityAddServiceModel.getCredit().get(i)));
                    this.accountRepository.saveAndFlush(all.get(z));
                }
            }
        }
        if (ledgerEntity.getContragent().getType().equals("vendors") && isInvoice==true){
            for (Account account : all) {
                if (account.getAccountNumber().equals("401")){
                    account.setCredit(account.getCredit().add(bigDecimal));
                    this.accountRepository.saveAndFlush(account);
                    accounts.add(account);
                    Credit creditVendor = new Credit(bigDecimal);
                    credits.add(creditVendor);
                    this.creditRepository.saveAndFlush(creditVendor);
                    for (int z = 0; z < allCon.size(); z++) {
                        Contragent contragent = allCon.get(z);
                        String contragents = ledgerEntityAddServiceModel.getContragents();
                        String name = allCon.get(z).getName();
                        if (contragents.equals(name)) {
                            contragent.setDebit(baseDebit);
                            contragent.setCredit(contragent.getCredit().add(bigDecimal));
                            this.contragentRepository.saveAndFlush(contragent);
                        }
                    }
                }
            }
        }

        if (ledgerEntity.getContragent().getType().equals("customer") && isInvoice==true){
            for (Account account : all) {
                if (account.getAccountNumber().equals("411")){
                    account.setDebit(account.getDebit().add(bigDecimal));
                    this.accountRepository.saveAndFlush(account);
                    accounts.add(account);
                    Debit debitCustomer = new Debit(bigDecimal);
                    debits.add(debitCustomer);
                    this.debitRepository.saveAndFlush(debitCustomer);
                    for (int z = 0; z < allCon.size(); z++) {
                        Contragent contragent = allCon.get(z);
                        String contragents = ledgerEntityAddServiceModel.getContragents();
                        String name = allCon.get(z).getName();
                        if (contragents.equals(name)) {
                            contragent.setDebit(contragent.getDebit().add(bigDecimal));
                            contragent.setCredit(baseCredit);
                           this.contragentRepository.saveAndFlush(contragent);
                        }
                    }
                }
            }
        }
        ledgerEntity.setAccounts(accounts);
        List<User> allUser = this.userRepository.findAll();
        for (int z = 0; z < allUser.size(); z++) {
            if (ledgerEntityAddServiceModel.getUser().equals(allUser.get(z).getUsername()))
                ledgerEntity.setUser(allUser.get(z));
        }
        ledgerEntity.setDocumentDate(ledgerEntityAddServiceModel.getDocumentDate());
            List<Description> descriptions = new LinkedList<>();
            List<String> importDescription = ledgerEntityAddServiceModel.getDescription();
        for (int i = 0; i < importDescription.size(); i++) {
            Description description = new Description(importDescription.get(i));
            descriptions.add(description);
            this.descriptionRepository.saveAndFlush(description);
        }
        ledgerEntity.setDescription(descriptions);
        this.ledgerEntityRepository.saveAndFlush(this.modelMapper.map(ledgerEntity,LedgerEntity.class));
        return ledgerEntityAddServiceModel;
    }

    @Override
    @Transactional
    public List<LedgerEntityForViewServiceModel> findAll() {
        List<LedgerEntity> allLedgerEntities = ledgerEntityRepository.findAll();
        List<LedgerEntityForViewServiceModel> ledgerEntityForViewServiceModels = new ArrayList<>();
        for (int i = 0; i < allLedgerEntities.size(); i++) {
            LedgerEntity ledgerEntity = allLedgerEntities.get(i);
            List<String> listLedger = new LinkedList<>();
            for (Account account : allLedgerEntities.get(i).getAccounts()) {
                listLedger.add(account.getName());
            }
            LedgerEntityForViewServiceModel ledgerEntityForViewServiceModel = new LedgerEntityForViewServiceModel();
            ledgerEntityForViewServiceModel.setAccounts(listLedger);
            ledgerEntityForViewServiceModel.setContragent(ledgerEntity.getContragent().getName());
            List<BigDecimal> creditLedgerEntity = new LinkedList<>();
            for (Credit credit : allLedgerEntities.get(i).getCredit()) {
                creditLedgerEntity.add(credit.getCredit());
            }
            ledgerEntityForViewServiceModel.setCredit(creditLedgerEntity);
            List<BigDecimal> debitLedgerEntity = new LinkedList<>();
            for (Debit debit : allLedgerEntities.get(i).getDebit()) {
                debitLedgerEntity.add(debit.getDebit());
            }
            ledgerEntityForViewServiceModel.setDebit(debitLedgerEntity);
            List<String> descriptions = new LinkedList<>();
            for (Description description : allLedgerEntities.get(i).getDescription()) {
                descriptions.add(description.getDescription());
            }
            ledgerEntityForViewServiceModel.setDescription(descriptions);
            ledgerEntityForViewServiceModel.setDocumentDate(ledgerEntity.getDocumentDate());
            ledgerEntityForViewServiceModel.setDocumentNumber(ledgerEntity.getDocumentNumber());
            ledgerEntityForViewServiceModel.setDocumentType(ledgerEntity.getDocumentType());
            ledgerEntityForViewServiceModel.setPostingDate(ledgerEntity.getPostingDate());
            ledgerEntityForViewServiceModel.setUser(ledgerEntity.getUser().getUsername());
            ledgerEntityForViewServiceModels.add(ledgerEntityForViewServiceModel);
        }
        return ledgerEntityForViewServiceModels;
    }

    @Override
    @Transactional
    public List<LedgerEntityForViewServiceModel> findAllByPayed() {
        List<LedgerEntity> allLedgerEntities = this.ledgerEntityRepository.findAllByDocumentType("Invoice");
        List<LedgerEntityForViewServiceModel> ledgerEntityForViewServiceModels = new ArrayList<>();
        for (int i = 0; i < allLedgerEntities.size(); i++) {
            if (allLedgerEntities.get(i).getContragent().getType()=="vendors" & allLedgerEntities.get(i).isPayed() == false){
            LedgerEntity ledgerEntity = allLedgerEntities.get(i);
            List<String> listLedger = new LinkedList<>();
            for (Account account : allLedgerEntities.get(i).getAccounts()) {
                listLedger.add(account.getName());
            }
            LedgerEntityForViewServiceModel ledgerEntityForViewServiceModel = new LedgerEntityForViewServiceModel();
            ledgerEntityForViewServiceModel.setAccounts(listLedger);
            ledgerEntityForViewServiceModel.setContragent(ledgerEntity.getContragent().getName());
            List<BigDecimal> creditLedgerEntity = new LinkedList<>();
            for (Credit credit : allLedgerEntities.get(i).getCredit()) {
                if (credit.getCredit().compareTo(BigDecimal.valueOf(0))>0) {
                    creditLedgerEntity.add(credit.getCredit());
                }
            }
            ledgerEntityForViewServiceModel.setCredit(creditLedgerEntity);
            List<BigDecimal> debitLedgerEntity = new LinkedList<>();
            for (Debit debit : allLedgerEntities.get(i).getDebit()) {
                debitLedgerEntity.add(debit.getDebit());
            }
            ledgerEntityForViewServiceModel.setDebit(debitLedgerEntity);
            List<String> descriptions = new LinkedList<>();
            for (Description description : allLedgerEntities.get(i).getDescription()) {
                descriptions.add(description.getDescription());
            }
            ledgerEntityForViewServiceModel.setDescription(descriptions);
            ledgerEntityForViewServiceModel.setDocumentDate(ledgerEntity.getDocumentDate());
            ledgerEntityForViewServiceModel.setDocumentNumber(ledgerEntity.getDocumentNumber());
            ledgerEntityForViewServiceModel.setDocumentType(ledgerEntity.getDocumentType());
            ledgerEntityForViewServiceModel.setPostingDate(ledgerEntity.getPostingDate());
            ledgerEntityForViewServiceModel.setUser(ledgerEntity.getUser().getUsername());
            ledgerEntityForViewServiceModel.setId(ledgerEntity.getId());
            ledgerEntityForViewServiceModels.add(ledgerEntityForViewServiceModel);
        }
        }
        return ledgerEntityForViewServiceModels;
    }

    @Override
    public void isApproved(String id) {
        LedgerEntity byId = this.ledgerEntityRepository.findById(id).orElse(null);
        byId.setPayed(true);
        this.ledgerEntityRepository.saveAndFlush(byId);
    }

    @Override
    public boolean existsUser(String ids) {
        Objects.requireNonNull(ids);
        return ledgerEntityRepository.findById(ids).isPresent();


    }

}

