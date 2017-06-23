package jp.co.kenshu.mapper;

import java.util.List;

import jp.co.kenshu.dto.test.TestDto;
import jp.co.kenshu.entity.Test;

public interface TestMapper {
    Test getTest(int id);

    //全件表示
    List<Test> getTestAll();

    //WHEREにオブジェクトをわたす
    Test getTestByDto(TestDto dto);

    //insert
    int insertTest(String name);

    //delete
    int deleteTest(int id);

    //update
    int updateTest(TestDto dto);


}