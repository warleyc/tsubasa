/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMatchResultCutinDeleteDialogComponent } from 'app/entities/m-match-result-cutin/m-match-result-cutin-delete-dialog.component';
import { MMatchResultCutinService } from 'app/entities/m-match-result-cutin/m-match-result-cutin.service';

describe('Component Tests', () => {
  describe('MMatchResultCutin Management Delete Component', () => {
    let comp: MMatchResultCutinDeleteDialogComponent;
    let fixture: ComponentFixture<MMatchResultCutinDeleteDialogComponent>;
    let service: MMatchResultCutinService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMatchResultCutinDeleteDialogComponent]
      })
        .overrideTemplate(MMatchResultCutinDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMatchResultCutinDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMatchResultCutinService);
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
