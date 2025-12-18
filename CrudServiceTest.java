package de.seuhd.campuscoffee.domain;

import de.seuhd.campuscoffee.domain.implementation.CrudServiceImpl;
import de.seuhd.campuscoffee.domain.models.GenericEntity;
import de.seuhd.campuscoffee.domain.ports.data.CrudDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CrudServiceTest {

    @Mock
    private CrudDataService<GenericEntity, Long> crudDataService;

    @InjectMocks
    private CrudServiceImpl<GenericEntity, Long> crudService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEntity_Success() {
        GenericEntity entity = new GenericEntity();
        when(crudDataService.create(entity)).thenReturn(entity);

        GenericEntity createdEntity = crudService.create(entity);

        assertNotNull(createdEntity);
        verify(crudDataService, times(1)).create(entity);
    }

    @Test
    void testReadEntity_Success() {
        Long entityId = 1L;
        GenericEntity entity = new GenericEntity();
        when(crudDataService.read(entityId)).thenReturn(Optional.of(entity));

        GenericEntity result = crudService.read(entityId);

        assertNotNull(result);
        verify(crudDataService, times(1)).read(entityId);
    }

    @Test
    void testReadEntity_EntityNotFound() {
        Long entityId = 1L;
        when(crudDataService.read(entityId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> crudService.read(entityId));
        verify(crudDataService, times(1)).read(entityId);
    }

    @Test
    void testUpdateEntity_Success() {
        GenericEntity entity = new GenericEntity();
        when(crudDataService.update(entity)).thenReturn(entity);

        GenericEntity updatedEntity = crudService.update(entity);

        assertNotNull(updatedEntity);
        verify(crudDataService, times(1)).update(entity);
    }

    @Test
    void testDeleteEntity_Success() {
        Long entityId = 1L;

        crudService.delete(entityId);

        verify(crudDataService, times(1)).delete(entityId);
    }

    @Test
    void testFindAllEntities() {
        List<GenericEntity> entities = List.of(new GenericEntity(), new GenericEntity());
        when(crudDataService.findAll()).thenReturn(entities);

        List<GenericEntity> result = crudService.findAll();

        assertEquals(2, result.size());
        verify(crudDataService, times(1)).findAll();
    }
}