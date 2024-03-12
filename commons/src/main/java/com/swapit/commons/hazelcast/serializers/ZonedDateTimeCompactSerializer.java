package com.swapit.commons.hazelcast.serializers;

import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import lombok.NonNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeCompactSerializer implements CompactSerializer<ZonedDateTime> {

    @Override
    @NonNull
    public ZonedDateTime read(CompactReader reader) {
        long epochSecond = reader.readInt64("epochSecond");
        int nano = reader.readInt32("nano");
        String zone = reader.readString("zone");
        return ZonedDateTime.ofInstant(java.time.Instant.ofEpochSecond(epochSecond, nano), ZoneId.of(zone));
    }

    @Override
    public void write(CompactWriter writer, ZonedDateTime zonedDateTime) {
        writer.writeInt64("epochSecond", zonedDateTime.toInstant().getEpochSecond());
        writer.writeInt32("nano", zonedDateTime.getNano());
        writer.writeString("zone", zonedDateTime.getZone().getId());
    }

    @Override
    @NonNull
    public String getTypeName() {
        return "zonedDateTime";
    }

    @Override
    @NonNull
    public Class<ZonedDateTime> getCompactClass() {
        return ZonedDateTime.class;
    }
}
