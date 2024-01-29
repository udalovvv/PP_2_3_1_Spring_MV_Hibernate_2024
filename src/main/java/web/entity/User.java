package web.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Не может быть пустым")
    @Size(min = 2, max = 20, message = "Длина имени доджна быть от 2 до 20 символов")
    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Не может быть пустым")
    @Size(min = 2, max = 20, message = "Длина фамилии доджна быть от 2 до 20 символов")
    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @Min(value = 0, message = "Возрат не должен быть меньше 0")
    @Max(value = 150, message = "Возраст не должен быть больше 150")
    @NonNull
    @Column(name = "age")
    private byte age;

    @NotEmpty(message = "Не может быть пустым")
    @Email(message = "Email не корректен")
    @NonNull
    @Column(name = "email")
    private String email;
}
