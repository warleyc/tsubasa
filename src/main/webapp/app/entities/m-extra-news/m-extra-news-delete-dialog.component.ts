import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMExtraNews } from 'app/shared/model/m-extra-news.model';
import { MExtraNewsService } from './m-extra-news.service';

@Component({
  selector: 'jhi-m-extra-news-delete-dialog',
  templateUrl: './m-extra-news-delete-dialog.component.html'
})
export class MExtraNewsDeleteDialogComponent {
  mExtraNews: IMExtraNews;

  constructor(
    protected mExtraNewsService: MExtraNewsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mExtraNewsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mExtraNewsListModification',
        content: 'Deleted an mExtraNews'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-extra-news-delete-popup',
  template: ''
})
export class MExtraNewsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mExtraNews }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MExtraNewsDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mExtraNews = mExtraNews;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-extra-news', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-extra-news', { outlets: { popup: null } }]);
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
