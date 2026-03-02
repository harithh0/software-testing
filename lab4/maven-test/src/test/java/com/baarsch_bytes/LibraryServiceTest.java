package com.baarsch_bytes;

import com.baarsch_bytes.Exceptions.DatabaseFailureException;
import com.baarsch_bytes.Exceptions.EmailFailureException;
import com.baarsch_bytes.Library.EmailProvider;
import com.baarsch_bytes.Library.LibraryService;
import com.baarsch_bytes.Library.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.crypto.Data;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    @Mock
    EmailProvider emailProviderMock;

    @Mock
    ResourceRepository resourceRepositoryMock;

    LibraryService service;

    @BeforeEach
    public void setUp() {
        service = new LibraryService(emailProviderMock, resourceRepositoryMock);
    }

    @Test
    public void checkoutResource_nullUUID_returnsFalse() throws EmailFailureException, DatabaseFailureException {
        assertFalse(service.checkoutResource(null, "test@gmail.com"));
    }

    @Test
    public void everythingTrue_returnsTrue() throws Exception  {
        UUID uuid = UUID.randomUUID();
        when(resourceRepositoryMock.isResourceAvailable(uuid)).thenReturn(true);
        when(resourceRepositoryMock.updateStatus(uuid, false)).thenReturn(true);
        when(emailProviderMock.sendEmail("test@gmail.com", "Resource ID: " + uuid + " checked out.")).thenReturn(true);
        assertTrue(service.checkoutResource(uuid, "test@gmail.com"));
    }

    @Test
    public void resourceAvailableFalse_throwsFalse() throws Exception  {
        UUID uuid = UUID.randomUUID();
        when(resourceRepositoryMock.isResourceAvailable(uuid)).thenReturn(false);
        assertFalse(service.checkoutResource(uuid, "test@gmail.com"));
    }


    @Test
    public void emailSentFalse_throwsException() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(resourceRepositoryMock.isResourceAvailable(uuid)).thenReturn(true);
        when(resourceRepositoryMock.updateStatus(uuid, false)).thenReturn(true);
        when(emailProviderMock.sendEmail("test@gmail.com", "Resource ID: " + uuid + " checked out.")).thenReturn(false);
        assertThrows(EmailFailureException.class, () -> { service.checkoutResource(uuid, "test@gmail.com");});
    }

    @Test
    public void UpdateStatusFalse_throwsException() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(resourceRepositoryMock.isResourceAvailable(uuid)).thenReturn(true);
        when(resourceRepositoryMock.updateStatus(uuid, false)).thenReturn(false);
        LibraryService service = new LibraryService(emailProviderMock, resourceRepositoryMock);
        assertThrows(DatabaseFailureException.class, () -> { service.checkoutResource(uuid, "test@gmail.com");});
    }


}

