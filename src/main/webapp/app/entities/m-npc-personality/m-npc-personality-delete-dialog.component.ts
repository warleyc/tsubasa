import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMNpcPersonality } from 'app/shared/model/m-npc-personality.model';
import { MNpcPersonalityService } from './m-npc-personality.service';

@Component({
  selector: 'jhi-m-npc-personality-delete-dialog',
  templateUrl: './m-npc-personality-delete-dialog.component.html'
})
export class MNpcPersonalityDeleteDialogComponent {
  mNpcPersonality: IMNpcPersonality;

  constructor(
    protected mNpcPersonalityService: MNpcPersonalityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mNpcPersonalityService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mNpcPersonalityListModification',
        content: 'Deleted an mNpcPersonality'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-npc-personality-delete-popup',
  template: ''
})
export class MNpcPersonalityDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mNpcPersonality }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MNpcPersonalityDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mNpcPersonality = mNpcPersonality;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-npc-personality', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-npc-personality', { outlets: { popup: null } }]);
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
