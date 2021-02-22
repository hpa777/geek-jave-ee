package ru.geekbrains.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

abstract class Repository {

    private final Map<Long, Entity> map = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public List<Entity> findAll() {
        return new ArrayList<>(map.values());
    }

    public Entity findById(Long id) {
        return map.get(id);
    }

    public void saveOrUpdate(Entity entity) {
        if (entity.getId() == null) {
            Long id = identity.incrementAndGet();
            entity.setId(id);
        }
        map.put(entity.getId(), entity);
    }

    public void deleteById(Long id) {
        map.remove(id);
    }

}
