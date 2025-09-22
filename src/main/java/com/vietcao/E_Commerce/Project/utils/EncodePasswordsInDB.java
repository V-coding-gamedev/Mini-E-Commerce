package com.vietcao.E_Commerce.Project.utils;

import java.sql.*;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vietcao.E_Commerce.Project.entities.User;
import com.vietcao.E_Commerce.Project.repositories.UserRepository;
import java.util.List;
import org.springframework.dao.DataAccessException;

@Component
public class EncodePasswordsInDB implements CommandLineRunner {

    @Autowired
    public DataSource dataSource;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        try (Connection con = dataSource.getConnection()) {
            System.out.println("Connection ok");

            String sql = "UPDATE Users SET password = ? WHERE username = ?";

            List<User> users = userRepository.findAll();

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (User user : users) {
                    try {
                        if (!user.getPassword().startsWith("$2a$")) {
                            String encodedPassword = passwordEncoder.encode(user.getPassword());

                            // set giá trị cho thứ tự dấu ? tương ứng trong câu SQL
                            ps.setString(1, encodedPassword);
                            ps.setString(2, user.getUsername());

                            int row = ps.executeUpdate(); // phương thức dùng để thực thi câu lệnh SQL kiểu update dữ liệu (INSERT, UPDATE, DELETE).
                            System.out.println("Updated password for user " + user.getUsername() + " - Row number: " + row);
                        } else {
                            System.out.println("Password has already been encoded");
                        }
                    } catch (Exception e) {
                        // bắt lỗi từng user để vòng lặp không dừng
                        System.err.println("Lỗi khi update user "
                                + user.getUsername()
                                + ": " + e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi kết nối hoặc thực thi câu lệnh: " + e.getMessage());
        } catch (DataAccessException e) {
            System.err.println("Lỗi khi truy vấn userRepository: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi không xác định: " + e.getMessage());
        }
    }
}
