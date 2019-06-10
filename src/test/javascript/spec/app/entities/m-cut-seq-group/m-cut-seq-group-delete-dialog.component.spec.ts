/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCutSeqGroupDeleteDialogComponent } from 'app/entities/m-cut-seq-group/m-cut-seq-group-delete-dialog.component';
import { MCutSeqGroupService } from 'app/entities/m-cut-seq-group/m-cut-seq-group.service';

describe('Component Tests', () => {
  describe('MCutSeqGroup Management Delete Component', () => {
    let comp: MCutSeqGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MCutSeqGroupDeleteDialogComponent>;
    let service: MCutSeqGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCutSeqGroupDeleteDialogComponent]
      })
        .overrideTemplate(MCutSeqGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCutSeqGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCutSeqGroupService);
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
