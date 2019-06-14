/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpPlayerStampDeleteDialogComponent } from 'app/entities/m-pvp-player-stamp/m-pvp-player-stamp-delete-dialog.component';
import { MPvpPlayerStampService } from 'app/entities/m-pvp-player-stamp/m-pvp-player-stamp.service';

describe('Component Tests', () => {
  describe('MPvpPlayerStamp Management Delete Component', () => {
    let comp: MPvpPlayerStampDeleteDialogComponent;
    let fixture: ComponentFixture<MPvpPlayerStampDeleteDialogComponent>;
    let service: MPvpPlayerStampService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpPlayerStampDeleteDialogComponent]
      })
        .overrideTemplate(MPvpPlayerStampDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpPlayerStampDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPvpPlayerStampService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
