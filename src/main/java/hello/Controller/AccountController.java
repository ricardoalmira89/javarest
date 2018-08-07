package hello.Controller;

import hello.Entity.Account;
import hello.Entity.Bookmark;
import hello.Exception.BookmarkNotFoundException;
import hello.Exception.UserNotFoundException;
import hello.Repository.AccountRepository;
import hello.Repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/accounts")
class AccountController {

    private final AccountRepository accountRepository;

    @Autowired
    AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    Collection<Account> indexAction() {
        return this.accountRepository.findAll();
    }

    @PostMapping
    Account newAction(@RequestBody Account input) {

        Account entity = new Account(input.getUsername(), input.getPassword());
        this.accountRepository.save(entity);
        return entity;

    }

    @GetMapping("/{id}")
    Account showAction( @PathVariable Long id) {

        return this.accountRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    boolean deleteAction( @PathVariable Long id){

        this.accountRepository.deleteById(id);
        return true;
    }
//
//    /**
//     * Verify the {@literal userId} exists.
//     *
//     * @param userId
//     */
//    private void validateUser(String userId) {
//        this.accountRepository
//                .findByUsername(userId)
//                .orElseThrow(() -> new UserNotFoundException(userId));
//    }
}