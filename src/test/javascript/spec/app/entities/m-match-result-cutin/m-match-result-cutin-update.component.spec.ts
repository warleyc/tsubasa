/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMatchResultCutinUpdateComponent } from 'app/entities/m-match-result-cutin/m-match-result-cutin-update.component';
import { MMatchResultCutinService } from 'app/entities/m-match-result-cutin/m-match-result-cutin.service';
import { MMatchResultCutin } from 'app/shared/model/m-match-result-cutin.model';

describe('Component Tests', () => {
  describe('MMatchResultCutin Management Update Component', () => {
    let comp: MMatchResultCutinUpdateComponent;
    let fixture: ComponentFixture<MMatchResultCutinUpdateComponent>;
    let service: MMatchResultCutinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMatchResultCutinUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMatchResultCutinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMatchResultCutinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMatchResultCutinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMatchResultCutin(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMatchResultCutin();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
