import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMatchOption } from 'app/shared/model/m-match-option.model';
import { MMatchOptionService } from './m-match-option.service';

@Component({
  selector: 'jhi-m-match-option-delete-dialog',
  templateUrl: './m-match-option-delete-dialog.component.html'
})
export class MMatchOptionDeleteDialogComponent {
  mMatchOption: IMMatchOption;

  constructor(
    protected mMatchOptionService: MMatchOptionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMatchOptionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMatchOptionListModification',
        content: 'Deleted an mMatchOption'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-match-option-delete-popup',
  template: ''
})
export class MMatchOptionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMatchOption }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMatchOptionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mMatchOption = mMatchOption;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-match-option', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-match-option', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
