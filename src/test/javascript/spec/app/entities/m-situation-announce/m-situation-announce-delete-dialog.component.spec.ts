/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MSituationAnnounceDeleteDialogComponent } from 'app/entities/m-situation-announce/m-situation-announce-delete-dialog.component';
import { MSituationAnnounceService } from 'app/entities/m-situation-announce/m-situation-announce.service';

describe('Component Tests', () => {
  describe('MSituationAnnounce Management Delete Component', () => {
    let comp: MSituationAnnounceDeleteDialogComponent;
    let fixture: ComponentFixture<MSituationAnnounceDeleteDialogComponent>;
    let service: MSituationAnnounceService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSituationAnnounceDeleteDialogComponent]
      })
        .overrideTemplate(MSituationAnnounceDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MSituationAnnounceDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MSituationAnnounceService);
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
