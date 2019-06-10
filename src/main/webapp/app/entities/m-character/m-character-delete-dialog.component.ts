import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCharacter } from 'app/shared/model/m-character.model';
import { MCharacterService } from './m-character.service';

@Component({
  selector: 'jhi-m-character-delete-dialog',
  templateUrl: './m-character-delete-dialog.component.html'
})
export class MCharacterDeleteDialogComponent {
  mCharacter: IMCharacter;

  constructor(
    protected mCharacterService: MCharacterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCharacterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCharacterListModification',
        content: 'Deleted an mCharacter'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-character-delete-popup',
  template: ''
})
export class MCharacterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCharacter }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCharacterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCharacter = mCharacter;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-character', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-character', { outlets: { popup: null } }]);
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
