/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpWatcherStampDeleteDialogComponent } from 'app/entities/m-pvp-watcher-stamp/m-pvp-watcher-stamp-delete-dialog.component';
import { MPvpWatcherStampService } from 'app/entities/m-pvp-watcher-stamp/m-pvp-watcher-stamp.service';

describe('Component Tests', () => {
  describe('MPvpWatcherStamp Management Delete Component', () => {
    let comp: MPvpWatcherStampDeleteDialogComponent;
    let fixture: ComponentFixture<MPvpWatcherStampDeleteDialogComponent>;
    let service: MPvpWatcherStampService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpWatcherStampDeleteDialogComponent]
      })
        .overrideTemplate(MPvpWatcherStampDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpWatcherStampDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPvpWatcherStampService);
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
