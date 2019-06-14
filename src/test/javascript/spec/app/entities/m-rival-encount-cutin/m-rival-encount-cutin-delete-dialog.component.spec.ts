/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MRivalEncountCutinDeleteDialogComponent } from 'app/entities/m-rival-encount-cutin/m-rival-encount-cutin-delete-dialog.component';
import { MRivalEncountCutinService } from 'app/entities/m-rival-encount-cutin/m-rival-encount-cutin.service';

describe('Component Tests', () => {
  describe('MRivalEncountCutin Management Delete Component', () => {
    let comp: MRivalEncountCutinDeleteDialogComponent;
    let fixture: ComponentFixture<MRivalEncountCutinDeleteDialogComponent>;
    let service: MRivalEncountCutinService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRivalEncountCutinDeleteDialogComponent]
      })
        .overrideTemplate(MRivalEncountCutinDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MRivalEncountCutinDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MRivalEncountCutinService);
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
