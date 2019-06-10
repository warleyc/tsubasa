/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryEnDeleteDialogComponent } from 'app/entities/m-dictionary-en/m-dictionary-en-delete-dialog.component';
import { MDictionaryEnService } from 'app/entities/m-dictionary-en/m-dictionary-en.service';

describe('Component Tests', () => {
  describe('MDictionaryEn Management Delete Component', () => {
    let comp: MDictionaryEnDeleteDialogComponent;
    let fixture: ComponentFixture<MDictionaryEnDeleteDialogComponent>;
    let service: MDictionaryEnService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryEnDeleteDialogComponent]
      })
        .overrideTemplate(MDictionaryEnDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryEnDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryEnService);
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
