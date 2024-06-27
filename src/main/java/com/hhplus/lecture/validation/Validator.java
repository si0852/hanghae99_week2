package com.hhplus.lecture.validation;

import com.hhplus.lecture.business.entity.Apply;
import com.hhplus.lecture.business.entity.Lecture;
import com.hhplus.lecture.business.entity.Users;
import com.hhplus.lecture.exception.*;
import com.hhplus.lecture.response.Response;
import com.hhplus.lecture.type.ValidationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Validator {

    private static final Logger log = LoggerFactory.getLogger(Validator.class);
    public static ValidationType getUserInfoValue(Users user) {
        if(user == null) return ValidationType.USER_INVALID;
        return ValidationType.VALID;
    }

    public static ValidationType getLectureInfoValue(Lecture lecture) {
        if(lecture == null) return ValidationType.LECTURE_INVALID;
//        else if(lecture.getMaxAttendees() < 1) return ValidationType.FULL_OF_PEOPLE;
        return ValidationType.VALID;
    }

    public static ValidationType openDateCompareToApplyDate(Date openDate, Date applyDate) {
        boolean compare = applyDate.before(openDate);
        if (compare) return ValidationType.NO_OPEN_DATE;
        return ValidationType.VALID;
    }

    public static ValidationType applyOpenValue(Apply apply) {
        if (apply != null && apply.getAttendanceYn().equals("Y")) return ValidationType.EXIST;
        return ValidationType.VALID;
    }

    public static void validate(ValidationType validationType) throws Exception {
        if(validationType.equals(ValidationType.LECTURE_INVALID))
            throw new NoLectureInfoException(new Response(500, "강의정보가 없습니다."));
        else if(validationType.equals(ValidationType.FULL_OF_PEOPLE))
            throw new FullOfPeopleException(new Response(500, "마감된 강의입니다."));
        else if(validationType.equals(ValidationType.NO_OPEN_DATE))
            throw new NoOpenDateException(new Response(500, "신청일이 아닙니다."));
        else if(validationType.equals(ValidationType.EXIST))
            throw new NotExistApplyInfoException(new Response(500, "신청내역이 존재합니다."));
    }


}
