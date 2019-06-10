/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCoopRoomStampDeleteDialogComponent } from 'app/entities/m-coop-room-stamp/m-coop-room-stamp-delete-dialog.component';
import { MCoopRoomStampService } from 'app/entities/m-coop-room-stamp/m-coop-room-stamp.service';

describe('Component Tests', () => {
  describe('MCoopRoomStamp Management Delete Component', () => {
    let comp: MCoopRoomStampDeleteDialogComponent;
    let fixture: ComponentFixture<MCoopRoomStampDeleteDialogComponent>;
    let service: MCoopRoomStampService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCoopRoomStampDeleteDialogComponent]
      })
        .overrideTemplate(MCoopRoomStampDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCoopRoomStampDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCoopRoomStampService);
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
