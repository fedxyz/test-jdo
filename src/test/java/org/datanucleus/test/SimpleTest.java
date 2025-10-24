package org.datanucleus.test;

import java.util.*;
import org.junit.*;
import javax.jdo.*;

import static org.junit.Assert.*;
import mydomain.model.*;
import org.datanucleus.util.NucleusLogger;

import org.joda.time.LocalDate;

public class SimpleTest
{
    @Test
    public void testSimple()
    {
        NucleusLogger.GENERAL.info(">> test START");
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("MyTest");

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();

            // Persist entity with LocalDate
            LocalDate date = new LocalDate(2025, 10, 16);
            TestEntity entity = new TestEntity(date);
            pm.makePersistent(entity);

            tx.commit();

            // Query with same LocalDate
            Query<TestEntity> query = pm.newQuery(TestEntity.class, "testDate == :date");
            query.setNamedParameters(Map.of("date", date));
            TestEntity found = query.executeUnique();

            System.out.println("Saved: " + date + ", Found: " + found.getTestDate());
            assertEquals(date, found.getTestDate());

        }
        catch (Throwable thr)
        {
            NucleusLogger.GENERAL.error(">> Exception in test", thr);
            fail("Failed test : " + thr.getMessage());
        }
        finally 
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }

        pmf.close();
        NucleusLogger.GENERAL.info(">> test END");
    }
}
