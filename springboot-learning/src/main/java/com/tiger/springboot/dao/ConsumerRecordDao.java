package com.tiger.springboot.dao;

import com.tiger.springboot.entity.ConsumerRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ConsumerRecordDao {

    boolean insert(ConsumerRecord record);

    int delete(List<Long> ids);

    /**
     * 查询所有用户记录
     *
     * @return
     */
    List<ConsumerRecord> selectAll();

    /**
     * 查询某个用户的记录
     *
     * @param user
     * @return
     */
    List<ConsumerRecord> selectByUser(String user);
    /**
     * 查询某段时间的记录
     *
     * @param start
     * @param end
     * @return
     */
    List<ConsumerRecord> selectByDate(Date start, Date end);

    /**
     * 查询指定类型的消费记录
     *
     * @param type
     * @return
     */
    List<ConsumerRecord> selectByType(String type);
    /**
     * 查询某个用户某段时间内的记录
     *
     * @param user
     * @param start
     * @param end
     * @return
     */
    List<ConsumerRecord> selectByUserAndDate(String user, Date start, Date end);



    /**
     * 查询某个用户某种类型的消费记录
     * @param user
     * @param type
     * @return
     */
    List<ConsumerRecord> selectByUserAndType(String user, String type);

}
