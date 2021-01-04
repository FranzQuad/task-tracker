<div class="container-fluid">

    <div class="container">
        <div class="container-fluid d-flex justify-content-between">
            <h3 class="mx-0" style="margin-top: 10px; margin-bottom: 0px;">${project.name}</h3>
            <button type="button" class="btn btn-dark float-right" style="background-color: orange; width: 80px;
                height: 33px; margin-top: 10px;" data-toggle="modal" data-target="#editName">
                Edit
            </button>
        </div>

        <hr>

        <div class="container-fluid">
            <h4>Description</h4>
            <p class="alert alert-info">${project.description}</p>
            <div class="d-flex justify-content-end">
                <button type="button" class="btn btn-dark" style="background-color: orange; width: 80px;
                height: 33px;" data-toggle="modal" data-target="#editDescription">
                    Edit
                </button>
            </div>
        </div>

        <hr>

        <div class="container-fluid">
            <h4>Participants</h4>
            Здесь должен быть список участников!
        </div>

        <hr>

        <button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#deleteProject">Delete</button>
    </div>

    <!-- Edit project name modal window -->
    <div id="editName" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit Description</h4>
                </div>
                <form action="/project/${project.id}/edit-name" method="post">
                    <div class="modal-body">
                        <div class="container-fluid">
                            <input name="name" type="text" class="form-control" value="${project.name}">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input type="submit" class="btn btn-default" style="background-color: orange">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Edit project description modal window -->
    <div id="editDescription" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit Description</h4>
                </div>
                <form action="/project/${project.id}/edit-description" method="post">
                    <div class="modal-body">
                        <div class="container-fluid">
                            <textarea name="description" class="form-control" id="editDescription" rows="5" >${project.description}</textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input type="submit" class="btn btn-default" style="background-color: orange">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Project delete modal window -->
    <div id="deleteProject" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title"> Are you sure?</h4>
                </div>
                <form action="/project/${project.id}/delete" method="post">
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                        <input type="submit" class="btn" style="background-color: red" value="Yes">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>