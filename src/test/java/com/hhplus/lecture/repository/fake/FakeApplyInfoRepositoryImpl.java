package com.hhplus.lecture.repository.fake;

import com.hhplus.lecture.entity.Apply;

import java.util.ArrayList;
import java.util.List;

public class FakeApplyInfoRepositoryImpl implements ApplyInfoRepository {
    private final List<Apply> table = new ArrayList<>();


    @Override
    public Apply save(Apply apply) {
        table.add(apply);
        return apply;
    }

    @Override
    public List<Apply> findById(long lcId, long userId) {
        return table.stream().filter(apply -> apply.getLmId() == lcId && apply.getUserId() == userId).toList();
    }
}
