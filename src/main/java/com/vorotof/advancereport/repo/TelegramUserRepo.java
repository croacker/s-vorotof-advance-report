package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.TelegramUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TelegramUserRepo extends CrudRepository<TelegramUser, Long> {

    Optional<TelegramUser> findByUserName(String name);

}
