package vn.kma.OcrSerach.repository;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.BoundStatementBuilder;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.mapper.MapperContext;
import com.datastax.oss.driver.api.mapper.entity.saving.NullSavingStrategy;
import com.datastax.oss.driver.internal.core.util.concurrent.BlockingOperation;
import com.datastax.oss.driver.internal.core.util.concurrent.CompletableFutures;
import com.datastax.oss.driver.internal.mapper.DaoBase;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.kma.OcrSerach.model.Form;
import vn.kma.OcrSerach.model.FormHelper__MapperGenerated;

/**
 * Generated by the DataStax driver mapper, do not edit directly.
 */
@SuppressWarnings("all")
public class FormRepositoryImpl__MapperGenerated extends DaoBase implements FormRepository {
  private static final Logger LOG = LoggerFactory.getLogger(FormRepositoryImpl__MapperGenerated.class);

  private final FormHelper__MapperGenerated formHelper;

  private final PreparedStatement findFormBySolrStatement;

  private final PreparedStatement saveStatement;

  private final PreparedStatement deleteByIdStatement;

  private FormRepositoryImpl__MapperGenerated(MapperContext context,
      FormHelper__MapperGenerated formHelper, PreparedStatement findFormBySolrStatement,
      PreparedStatement saveStatement, PreparedStatement deleteByIdStatement) {
    super(context);
    this.formHelper = formHelper;
    this.findFormBySolrStatement = findFormBySolrStatement;
    this.saveStatement = saveStatement;
    this.deleteByIdStatement = deleteByIdStatement;
  }

  @Override
  public PagingIterable<Form> findFormBySolr(String search) {
    BoundStatementBuilder boundStatementBuilder = findFormBySolrStatement.boundStatementBuilder();
    boundStatementBuilder = boundStatementBuilder.set("search", search, String.class);
    BoundStatement boundStatement = boundStatementBuilder.build();
    return executeAndMapToEntityIterable(boundStatement, formHelper);
  }

  @Override
  public void save(Form form) {
    BoundStatementBuilder boundStatementBuilder = saveStatement.boundStatementBuilder();
    formHelper.set(form, boundStatementBuilder, NullSavingStrategy.DO_NOT_SET, false);
    BoundStatement boundStatement = boundStatementBuilder.build();
    execute(boundStatement);
  }

  @Override
  public void deleteById(String id) {
    BoundStatementBuilder boundStatementBuilder = deleteByIdStatement.boundStatementBuilder();
    boundStatementBuilder = boundStatementBuilder.set("id", id, String.class);
    BoundStatement boundStatement = boundStatementBuilder.build();
    execute(boundStatement);
  }

  public static CompletableFuture<FormRepository> initAsync(MapperContext context) {
    LOG.debug("[{}] Initializing new instance for keyspace = {} and table = {}",
        context.getSession().getName(),
        context.getKeyspaceId(),
        context.getTableId());
    throwIfProtocolVersionV3(context);
    try {
      // Initialize all entity helpers
      FormHelper__MapperGenerated formHelper = new FormHelper__MapperGenerated(context);
      if ((Boolean)context.getCustomState().get("datastax.mapper.schemaValidationEnabled")) {
        formHelper.validateEntityFields();
      }
      List<CompletionStage<PreparedStatement>> prepareStages = new ArrayList<>();
      // Prepare the statement for `findFormBySolr(java.lang.String)`:
      SimpleStatement findFormBySolrStatement_simple = formHelper.selectStart().whereRaw("solr_query  = :search").build();
      LOG.debug("[{}] Preparing query `{}` for method findFormBySolr(java.lang.String)",
          context.getSession().getName(),
          findFormBySolrStatement_simple.getQuery());
      CompletionStage<PreparedStatement> findFormBySolrStatement = prepare(findFormBySolrStatement_simple, context);
      prepareStages.add(findFormBySolrStatement);
      // Prepare the statement for `save(vn.kma.OcrSerach.model.Form)`:
      SimpleStatement saveStatement_simple = formHelper.insert().build();
      LOG.debug("[{}] Preparing query `{}` for method save(vn.kma.OcrSerach.model.Form)",
          context.getSession().getName(),
          saveStatement_simple.getQuery());
      CompletionStage<PreparedStatement> saveStatement = prepare(saveStatement_simple, context);
      prepareStages.add(saveStatement);
      // Prepare the statement for `deleteById(java.lang.String)`:
      SimpleStatement deleteByIdStatement_simple = formHelper.deleteByPrimaryKeyParts(1).build();
      LOG.debug("[{}] Preparing query `{}` for method deleteById(java.lang.String)",
          context.getSession().getName(),
          deleteByIdStatement_simple.getQuery());
      CompletionStage<PreparedStatement> deleteByIdStatement = prepare(deleteByIdStatement_simple, context);
      prepareStages.add(deleteByIdStatement);
      // Initialize all method invokers
      // Build the DAO when all statements are prepared
      return CompletableFutures.allSuccessful(prepareStages)
          .thenApply(v -> (FormRepository) new FormRepositoryImpl__MapperGenerated(context,
              formHelper,
              CompletableFutures.getCompleted(findFormBySolrStatement),
              CompletableFutures.getCompleted(saveStatement),
              CompletableFutures.getCompleted(deleteByIdStatement)))
          .toCompletableFuture();
    } catch (Throwable t) {
      return CompletableFutures.failedFuture(t);
    }
  }

  public static FormRepository init(MapperContext context) {
    BlockingOperation.checkNotDriverThread();
    return CompletableFutures.getUninterruptibly(initAsync(context));
  }
}