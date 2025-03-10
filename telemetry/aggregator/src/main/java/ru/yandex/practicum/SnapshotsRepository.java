package ru.yandex.practicum;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.kafka.telemetry.event.SensorsSnapshotAvro;

@Component
public class SnapshotsRepository {
    private final Map<String, SensorsSnapshotAvro> snapshots = new HashMap<>();

    public Optional<SensorsSnapshotAvro> get(String hubId) {
        return Optional.ofNullable(snapshots.get(hubId));
    }

    public SensorsSnapshotAvro update(String hubId, SensorsSnapshotAvro sensorsSnapshotAvro) {
        return snapshots.put(hubId, sensorsSnapshotAvro);
    }
}
