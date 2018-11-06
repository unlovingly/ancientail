package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.slip.{
  SlipId,
  SlipRepository,
  Slip => Entity,
  SlipItem => EntityItem
}
import com.example.manything.roundelayout.domain.Identifiability
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

//abstract class SlipRepositoryWithSlick(
//  implicit val db: Database,
//  implicit val executionContext: ExecutionContext)
//  extends SlipRepository[EitherAppliedFuture] {
//  override def retrieve(): EitherAppliedFuture[Seq[Entity[A]]]
//
//  override def retrieve(id: Seq[SlipId]): EitherAppliedFuture[Seq[Entity[A]]]
//
//  override def store(entity: Entity[A]): EitherAppliedFuture[Entity[A]]
//}
