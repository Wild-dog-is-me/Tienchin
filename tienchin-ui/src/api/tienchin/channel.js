import request from '@/utils/request'

export function listChannel(query) {
    return request({
        url: '/tienchin/channel/list',
        method: 'get',
        params: query
    })
}

export function getChannel(channelId) {
    return request({
        url: '/tienchin/channel/' + channelId,
        method: 'get'
    })
}

export function addChannel(data) {
    return request({
        url: '/tienchin/channel',
        method: 'post',
        data: data
    })
}

export function updateChannel(data) {
    return request({
        url: '/tienchin/channel',
        method: 'put',
        data: data
    })
}

export function delChannel(channelId) {
    return request({
        url: '/tienchin/channel/' + channelId,
        method: 'delete'
    })
}


