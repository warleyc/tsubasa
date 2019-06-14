/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MRecommendFormationFilterElementDeleteDialogComponent } from 'app/entities/m-recommend-formation-filter-element/m-recommend-formation-filter-element-delete-dialog.component';
import { MRecommendFormationFilterElementService } from 'app/entities/m-recommend-formation-filter-element/m-recommend-formation-filter-element.service';

describe('Component Tests', () => {
  describe('MRecommendFormationFilterElement Management Delete Component', () => {
    let comp: MRecommendFormationFilterElementDeleteDialogComponent;
    let fixture: ComponentFixture<MRecommendFormationFilterElementDeleteDialogComponent>;
    let service: MRecommendFormationFilterElementService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRecommendFormationFilterElementDeleteDialogComponent]
      })
        .overrideTemplate(MRecommendFormationFilterElementDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MRecommendFormationFilterElementDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MRecommendFormationFilterElementService);
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
