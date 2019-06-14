/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMatchOptionDeleteDialogComponent } from 'app/entities/m-match-option/m-match-option-delete-dialog.component';
import { MMatchOptionService } from 'app/entities/m-match-option/m-match-option.service';

describe('Component Tests', () => {
  describe('MMatchOption Management Delete Component', () => {
    let comp: MMatchOptionDeleteDialogComponent;
    let fixture: ComponentFixture<MMatchOptionDeleteDialogComponent>;
    let service: MMatchOptionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMatchOptionDeleteDialogComponent]
      })
        .overrideTemplate(MMatchOptionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMatchOptionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMatchOptionService);
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
