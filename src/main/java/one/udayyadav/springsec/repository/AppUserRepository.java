package one.udayyadav.springsec.repository;

import one.udayyadav.springsec.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
}
