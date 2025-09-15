/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vietcao.E_Commerce.Project.repositories;

import com.vietcao.E_Commerce.Project.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //    chỉ viết thêm method khi muốn query đặc thù (ví dụ: findByUsername(String username)).
    
    // Không cần viết lại, vì JpaRepository đã có sẵn findAll() rồi.
        List<User> findAll(); 
}
