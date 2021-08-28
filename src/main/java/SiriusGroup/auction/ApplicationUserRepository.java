package SiriusGroup.auction;

import SiriusGroup.auction.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
    public ApplicationUser findByUsername(String username);


}
