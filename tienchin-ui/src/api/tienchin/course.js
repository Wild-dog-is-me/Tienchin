import request from "../../utils/request";

export function listCourse(query) {
    return request({
        url: "/tienchin/course/list",
        method: 'GET',
        params: query
    })
}

export function getCourse(courseId) {
    return request({
        url: "/tienchin/course/" + courseId,
        method: 'GET'
    })
}

export function addCourse(data) {
    return request({
        url: "/tienchin/course",
        method: 'POST',
        data: data
    })
}

export function updateCourse(data) {
    return request({
        url: "/tienchin/course",
        method: 'PUT',
        data: data
    })
}

export function delCourse(courseId) {
    return request({
        url: "/tienchin/course/" + courseId,
        method: 'DELETE'
    })
}
