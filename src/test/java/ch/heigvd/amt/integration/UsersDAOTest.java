package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.Movie;
import ch.heigvd.amt.model.User;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class UsersDAOTest {
    @EJB
    private IUsersDAO usersManager;

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAUser() throws DuplicateKeyException {
        User user = User.builder().lastName("tot").firstName("tot").email("toto3@tata.titi").password("12345").build();
        usersManager.create(user);
    }
}
