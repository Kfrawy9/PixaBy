package com.elkfrawy.pixaby.domain.service

interface Downloader {

    fun downloadFile(url: String): Long

}