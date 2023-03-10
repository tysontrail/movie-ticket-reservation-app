package course.ensf607.assignment6.service;

import course.ensf607.assignment6.entity.FinancialInst;
import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.repository.FinancialInstRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Financial Institution service for verifying account info, is a controller class but is a service
 * class.
 */
@Service
public class FinancialInstService {

  @Autowired private FinancialInstRepository financialRepository;

  public FinancialInstService(FinancialInstRepository financialRepository) {
    this.financialRepository = financialRepository;
  }

  public boolean verify(User user) {
    Optional<FinancialInst> financialAcct =
        financialRepository.findByCreditCard(user.getCreditCard());
    if (financialAcct.isPresent()) {
      return checkFinancialDetails(financialAcct.get(), user);
    }
    return false;
  }

  public boolean checkFinancialDetails(FinancialInst financialAcct, User user) {
    if (!financialAcct.getFirstName().equals(user.getFirstName())) return false;
    if (!financialAcct.getLastName().equals(user.getLastName())) return false;
    if (financialAcct.getCvcNumber() != user.getCvcNumber()) return false;
    if (financialAcct.getExpiryDate() != user.getExpiryDate()) return false;

    return true;
  }
}
