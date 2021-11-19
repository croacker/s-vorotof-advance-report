package com.vorotof.advancereport.service;

import com.vorotof.advancereport.repo.TelegramUserRepo;
import com.vorotof.advancereport.service.dto.telegramuser.AddTelegramUserDto;
import com.vorotof.advancereport.service.dto.telegramuser.TelegramUserDto;
import com.vorotof.advancereport.service.mapper.telegramuser.AddDtoToTelegramUser;
import com.vorotof.advancereport.service.mapper.telegramuser.TelegramUserToDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Slf4j
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserRepo repo;

    private final TelegramUserToDto toDtoMapper;

    private final AddDtoToTelegramUser addToEntityMapper;

    @Override
    public List<TelegramUserDto> findAll(Pageable pageable) {
        return StreamSupport.stream(repo.findAll().spliterator(), false).map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public Mono<Long> getCount() {
        return Mono.just(repo.count());
    }

    @Override
    public Optional<TelegramUserDto> findOne(Long id) {
        return repo.findById(id).map(toDtoMapper);
    }

    @Override
    public Optional<TelegramUserDto> findByName(String name) {
        return repo.findByUserName(name).map(toDtoMapper);
    }

    @Override
    public TelegramUserDto save(AddTelegramUserDto dto) {
        var telegramUser = addToEntityMapper.map(dto);
        telegramUser = repo.save(telegramUser);
        return toDtoMapper.map(telegramUser);
    }

}
