package vn.kma.OcrSerach.model;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.data.GettableByName;
import com.datastax.oss.driver.api.core.data.SettableByName;
import com.datastax.oss.driver.api.core.metadata.schema.KeyspaceMetadata;
import com.datastax.oss.driver.api.core.metadata.schema.TableMetadata;
import com.datastax.oss.driver.api.core.type.DataType;
import com.datastax.oss.driver.api.core.type.UserDefinedType;
import com.datastax.oss.driver.api.core.type.reflect.GenericType;
import com.datastax.oss.driver.api.mapper.MapperContext;
import com.datastax.oss.driver.api.mapper.MapperException;
import com.datastax.oss.driver.api.mapper.entity.saving.NullSavingStrategy;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.delete.DeleteSelection;
import com.datastax.oss.driver.api.querybuilder.insert.InsertInto;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.select.SelectFrom;
import com.datastax.oss.driver.api.querybuilder.update.UpdateStart;
import com.datastax.oss.driver.internal.mapper.entity.EntityHelperBase;
import com.datastax.oss.driver.internal.querybuilder.update.DefaultUpdate;
import com.datastax.oss.driver.shaded.guava.common.collect.ImmutableList;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generated by the DataStax driver mapper, do not edit directly.
 */
@SuppressWarnings("all")
public class StudentHelper__MapperGenerated extends EntityHelperBase<Document> {
  private static final Logger LOG = LoggerFactory.getLogger(StudentHelper__MapperGenerated.class);

  private static final GenericType<LocalDate> GENERIC_TYPE = new GenericType<LocalDate>(){};

  private static final GenericType<String> GENERIC_TYPE1 = new GenericType<String>(){};

  private final List<String> primaryKeys;

  public StudentHelper__MapperGenerated(MapperContext context) {
    super(context, "kma", "student");
    LOG.debug("[{}] Entity Student will be mapped to {}{}",
        context.getSession().getName(),
        getKeyspaceId() == null ? "" : getKeyspaceId() + ".",
        getTableId());
    this.primaryKeys = ImmutableList.<String>builder()
        .add("classs")
        .add("id")
        .build();
  }

  @Override
  public Class<Document> getEntityClass() {
    return Document.class;
  }

  @Override
  public <SettableT extends SettableByName<SettableT>> SettableT set(Document entity,
                                                                     SettableT target, NullSavingStrategy nullSavingStrategy, boolean lenient) {
    if (!lenient || hasProperty(target, "classs")) {
      if (entity.getClasss() != null || nullSavingStrategy == NullSavingStrategy.SET_TO_NULL) {
        target = target.set("classs", entity.getClasss(), String.class);
      }
    }
    if (!lenient || hasProperty(target, "id")) {
      if (entity.getId() != null || nullSavingStrategy == NullSavingStrategy.SET_TO_NULL) {
        target = target.set("id", entity.getId(), String.class);
      }
    }
    if (!lenient || hasProperty(target, "course")) {
      if (entity.getCourse() != null || nullSavingStrategy == NullSavingStrategy.SET_TO_NULL) {
        target = target.set("course", entity.getCourse(), String.class);
      }
    }
    if (!lenient || hasProperty(target, "name")) {
      if (entity.getName() != null || nullSavingStrategy == NullSavingStrategy.SET_TO_NULL) {
        target = target.set("name", entity.getName(), String.class);
      }
    }
    if (!lenient || hasProperty(target, "expiry")) {
      if (entity.getExpiry() != null || nullSavingStrategy == NullSavingStrategy.SET_TO_NULL) {
        target = target.set("expiry", entity.getExpiry(), LocalDate.class);
      }
    }
    return target;
  }

  @Override
  public Document get(GettableByName source, boolean lenient) {
    Document returnValue = new Document();
    if (!lenient || hasProperty(source, "classs")) {
      String propertyValue = source.get("classs", String.class);
      returnValue.setClasss(propertyValue);
    }
    if (!lenient || hasProperty(source, "id")) {
      String propertyValue1 = source.get("id", String.class);
      returnValue.setId(propertyValue1);
    }
    if (!lenient || hasProperty(source, "course")) {
      String propertyValue2 = source.get("course", String.class);
      returnValue.setCourse(propertyValue2);
    }
    if (!lenient || hasProperty(source, "name")) {
      String propertyValue3 = source.get("name", String.class);
      returnValue.setName(propertyValue3);
    }
    if (!lenient || hasProperty(source, "expiry")) {
      LocalDate propertyValue4 = source.get("expiry", LocalDate.class);
      returnValue.setExpiry(propertyValue4);
    }
    return returnValue;
  }

  @Override
  public RegularInsert insert() {
    throwIfKeyspaceMissing();
    InsertInto insertInto = (keyspaceId == null)
        ? QueryBuilder.insertInto(tableId)
        : QueryBuilder.insertInto(keyspaceId, tableId);
    return insertInto
        .value("classs", QueryBuilder.bindMarker("classs"))
        .value("id", QueryBuilder.bindMarker("id"))
        .value("course", QueryBuilder.bindMarker("course"))
        .value("name", QueryBuilder.bindMarker("name"))
        .value("expiry", QueryBuilder.bindMarker("expiry"));
  }

  public Select selectByPrimaryKeyParts(int parameterCount) {
    Select select = selectStart();
    for (int i = 0; i < parameterCount && i < primaryKeys.size(); i++) {
      String columnName = primaryKeys.get(i);
      select = select.whereColumn(columnName).isEqualTo(QueryBuilder.bindMarker(columnName));
    }
    return select;
  }

  @Override
  public Select selectByPrimaryKey() {
    return selectByPrimaryKeyParts(primaryKeys.size());
  }

  @Override
  public Select selectStart() {
    throwIfKeyspaceMissing();
    SelectFrom selectFrom = (keyspaceId == null)
        ? QueryBuilder.selectFrom(tableId)
        : QueryBuilder.selectFrom(keyspaceId, tableId);
    return selectFrom
        .column("classs")
        .column("id")
        .column("course")
        .column("name")
        .column("expiry");
  }

  public DeleteSelection deleteStart() {
    throwIfKeyspaceMissing();
    return (keyspaceId == null)
        ? QueryBuilder.deleteFrom(tableId)
        : QueryBuilder.deleteFrom(keyspaceId, tableId);
  }

  public Delete deleteByPrimaryKeyParts(int parameterCount) {
    if (parameterCount <= 0) {
      throw new MapperException("parameterCount must be greater than 0");
    }
    DeleteSelection deleteSelection = deleteStart();
    String columnName = primaryKeys.get(0);
    Delete delete = deleteSelection.whereColumn(columnName).isEqualTo(QueryBuilder.bindMarker(columnName));
    for (int i = 1; i < parameterCount && i < primaryKeys.size(); i++) {
      columnName = primaryKeys.get(i);
      delete = delete.whereColumn(columnName).isEqualTo(QueryBuilder.bindMarker(columnName));
    }
    return delete;
  }

  @Override
  public Delete deleteByPrimaryKey() {
    return deleteByPrimaryKeyParts(primaryKeys.size());
  }

  @Override
  public DefaultUpdate updateStart() {
    throwIfKeyspaceMissing();
    UpdateStart update = (keyspaceId == null)
        ? QueryBuilder.update(tableId)
        : QueryBuilder.update(keyspaceId, tableId);
    return ((DefaultUpdate)update
        .setColumn("course", QueryBuilder.bindMarker("course"))
        .setColumn("name", QueryBuilder.bindMarker("name"))
        .setColumn("expiry", QueryBuilder.bindMarker("expiry")));
  }

  @Override
  public DefaultUpdate updateByPrimaryKey() {
    return ((DefaultUpdate)updateStart()
        .where(Relation.column("classs").isEqualTo(QueryBuilder.bindMarker("classs")))
        .where(Relation.column("id").isEqualTo(QueryBuilder.bindMarker("id"))));
  }

  @Override
  public void validateEntityFields() {
    CqlIdentifier keyspaceId = this.keyspaceId != null ? this.keyspaceId : context.getSession().getKeyspace().orElse(null);
    String entityClassName = "vn.kma.OcrSerach.model.Student";
    if (keyspaceId == null) {
      LOG.warn("[{}] Unable to validate table: {} for the entity class: {} because the keyspace is unknown (the entity does not declare a default keyspace, and neither the session nor the DAO were created with a keyspace). The DAO will only work if it uses fully-qualified queries with @Query or @QueryProvider.",
          context.getSession().getName(),
          tableId,
          entityClassName);
      return;
    }
    if(!keyspaceNamePresent(context.getSession().getMetadata().getKeyspaces(), keyspaceId)) {
      LOG.warn("[{}] Unable to validate table: {} for the entity class: {} because the session metadata has no information about the keyspace: {}.",
          context.getSession().getName(),
          tableId,
          entityClassName,
          keyspaceId);
      return;
    }
    Optional<KeyspaceMetadata> keyspace = context.getSession().getMetadata().getKeyspace(keyspaceId);
    List<CqlIdentifier> expectedCqlNames = new ArrayList<>();
    expectedCqlNames.add(CqlIdentifier.fromCql("classs"));
    expectedCqlNames.add(CqlIdentifier.fromCql("id"));
    expectedCqlNames.add(CqlIdentifier.fromCql("course"));
    expectedCqlNames.add(CqlIdentifier.fromCql("name"));
    expectedCqlNames.add(CqlIdentifier.fromCql("expiry"));
    Optional<TableMetadata> tableMetadata = keyspace.flatMap(v -> v.getTable(tableId));
    Optional<UserDefinedType> userDefinedType = keyspace.flatMap(v -> v.getUserDefinedType(tableId));
    if (tableMetadata.isPresent()) {
      // validation of missing Clustering Columns
      List<CqlIdentifier> expectedCqlClusteringColumns = new ArrayList<>();
      expectedCqlClusteringColumns.add(CqlIdentifier.fromCql("id"));
      List<CqlIdentifier> missingTableClusteringColumnNames = findMissingColumns(expectedCqlClusteringColumns, tableMetadata.get().getClusteringColumns().keySet());
      if (!missingTableClusteringColumnNames.isEmpty()) {
        throw new IllegalArgumentException(String.format("The CQL ks.table: %s.%s has missing Clustering columns: %s that are defined in the entity class: %s", keyspaceId, tableId, missingTableClusteringColumnNames, entityClassName));
      }
      // validation of missing PKs
      List<CqlIdentifier> expectedCqlPKs = new ArrayList<>();
      expectedCqlPKs.add(CqlIdentifier.fromCql("classs"));
      List<CqlIdentifier> missingTablePksNames = findMissingColumns(expectedCqlPKs, tableMetadata.get().getPartitionKey());
      if (!missingTablePksNames.isEmpty()) {
        throw new IllegalArgumentException(String.format("The CQL ks.table: %s.%s has missing Primary Key columns: %s that are defined in the entity class: %s", keyspaceId, tableId, missingTablePksNames, entityClassName));
      }
      // validation of all columns
      List<CqlIdentifier> missingTableCqlNames = findMissingCqlIdentifiers(expectedCqlNames, tableMetadata.get().getColumns().keySet());
      if (!missingTableCqlNames.isEmpty()) {
        throw new IllegalArgumentException(String.format("The CQL ks.table: %s.%s has missing columns: %s that are defined in the entity class: %s", keyspaceId, tableId, missingTableCqlNames, entityClassName));
      }
      // validation of types
      Map<CqlIdentifier, GenericType<?>> expectedTypesPerColumn = new LinkedHashMap<>();
      expectedTypesPerColumn.put(CqlIdentifier.fromCql("expiry"), GENERIC_TYPE);
      expectedTypesPerColumn.put(CqlIdentifier.fromCql("id"), GENERIC_TYPE1);
      expectedTypesPerColumn.put(CqlIdentifier.fromCql("course"), GENERIC_TYPE1);
      expectedTypesPerColumn.put(CqlIdentifier.fromCql("name"), GENERIC_TYPE1);
      expectedTypesPerColumn.put(CqlIdentifier.fromCql("classs"), GENERIC_TYPE1);
      List<String> missingTableTypes = findTypeMismatches(expectedTypesPerColumn, tableMetadata.get().getColumns(), context.getSession().getContext().getCodecRegistry());
      throwMissingTableTypesIfNotEmpty(missingTableTypes, keyspaceId, tableId, entityClassName);
    }
    else if (userDefinedType.isPresent()) {
      // validation of UDT columns
      List<CqlIdentifier> columns = userDefinedType.get().getFieldNames();
      List<CqlIdentifier> missingTableCqlNames = findMissingCqlIdentifiers(expectedCqlNames, columns);
      if (!missingTableCqlNames.isEmpty()) {
        throw new IllegalArgumentException(String.format("The CQL ks.udt: %s.%s has missing columns: %s that are defined in the entity class: %s", keyspaceId, tableId, missingTableCqlNames, entityClassName));
      }
      // validation of UDT types
      Map<CqlIdentifier, GenericType<?>> expectedTypesPerColumn = new LinkedHashMap<>();
      expectedTypesPerColumn.put(CqlIdentifier.fromCql("expiry"), GENERIC_TYPE);
      expectedTypesPerColumn.put(CqlIdentifier.fromCql("id"), GENERIC_TYPE1);
      expectedTypesPerColumn.put(CqlIdentifier.fromCql("course"), GENERIC_TYPE1);
      expectedTypesPerColumn.put(CqlIdentifier.fromCql("name"), GENERIC_TYPE1);
      expectedTypesPerColumn.put(CqlIdentifier.fromCql("classs"), GENERIC_TYPE1);
      List<CqlIdentifier> expectedColumns = userDefinedType.get().getFieldNames();
      List<DataType> expectedTypes = userDefinedType.get().getFieldTypes();
      List<String> missingTableTypes = findTypeMismatches(expectedTypesPerColumn, expectedColumns, expectedTypes, context.getSession().getContext().getCodecRegistry());
      throwMissingUdtTypesIfNotEmpty(missingTableTypes, keyspaceId, tableId, entityClassName);
    }
    // warn if there is not keyspace.table for defined entity - it means that table is missing, or schema it out of date.
    else {
      LOG.warn("[{}] There is no ks.table or UDT: {}.{} for the entity class: {}, or metadata is out of date.",
          context.getSession().getName(),
          keyspaceId,
          tableId,
          entityClassName);
    }
  }
}
