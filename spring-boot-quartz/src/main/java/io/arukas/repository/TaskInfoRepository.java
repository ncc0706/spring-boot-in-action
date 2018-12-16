package io.arukas.repository;

import io.arukas.entity.TaskInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/12/16 15:11 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
@Repository
public interface TaskInfoRepository extends JpaRepository<TaskInfo, String> {

    Page<TaskInfo> findAll(Pageable pageable);

}
