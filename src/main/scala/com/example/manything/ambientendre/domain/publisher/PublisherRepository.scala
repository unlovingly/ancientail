package com.example.manything.ambientendre.domain.publisher

import java.util.UUID

import com.example.manything.roundelayout.domain.Repository

trait PublisherRepository[A[_]] extends Repository[Publisher, UUID, A] {}
