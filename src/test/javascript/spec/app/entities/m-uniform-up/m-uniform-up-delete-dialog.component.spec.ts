/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MUniformUpDeleteDialogComponent } from 'app/entities/m-uniform-up/m-uniform-up-delete-dialog.component';
import { MUniformUpService } from 'app/entities/m-uniform-up/m-uniform-up.service';

describe('Component Tests', () => {
  describe('MUniformUp Management Delete Component', () => {
    let comp: MUniformUpDeleteDialogComponent;
    let fixture: ComponentFixture<MUniformUpDeleteDialogComponent>;
    let service: MUniformUpService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUniformUpDeleteDialogComponent]
      })
        .overrideTemplate(MUniformUpDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MUniformUpDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MUniformUpService);
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
