/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbexercises;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Evan
 * Simple Stateless bean to test EJB layer
 * Created: 10/10/2013
 * Revised: original code
 */
@Stateless
@LocalBean
public class StatelessFundManagerBean {

    public double addFunds(double balance, double amount) {
        balance += amount;
        return balance;
    }

    public double withdrawFunds(double balance, double amount) {
        if (balance < 0) {
            return 0.0;
        } else {
            balance -= amount;
            return balance;
        }
    }
}
