package SiriusGroup.auction;

import SiriusGroup.auction.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
    public ApplicationUser findByUsername(String username);

}
