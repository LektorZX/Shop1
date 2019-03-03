package root.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import root.entity.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
