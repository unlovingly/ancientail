package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.slip.SlipRepository
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

class SlipRepositoryWithSlick(implicit val db: Database,
                              implicit val executionContext: ExecutionContext)
  extends SlipRepository[EitherAppliedFuture] {
  override def retrieve(): EitherAppliedFuture[Seq[EntityType]] = ???
  override def retrieve(
    id: Seq[Identifier]): EitherAppliedFuture[Seq[EntityType]] = ???

  override def store(entity: EntityType): EitherAppliedFuture[EntityType] = ???
}
