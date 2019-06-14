/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MNgWordDeleteDialogComponent } from 'app/entities/m-ng-word/m-ng-word-delete-dialog.component';
import { MNgWordService } from 'app/entities/m-ng-word/m-ng-word.service';

describe('Component Tests', () => {
  describe('MNgWord Management Delete Component', () => {
    let comp: MNgWordDeleteDialogComponent;
    let fixture: ComponentFixture<MNgWordDeleteDialogComponent>;
    let service: MNgWordService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MNgWordDeleteDialogComponent]
      })
        .overrideTemplate(MNgWordDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MNgWordDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MNgWordService);
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
