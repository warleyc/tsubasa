/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDistributeParamPointUpdateComponent } from 'app/entities/m-distribute-param-point/m-distribute-param-point-update.component';
import { MDistributeParamPointService } from 'app/entities/m-distribute-param-point/m-distribute-param-point.service';
import { MDistributeParamPoint } from 'app/shared/model/m-distribute-param-point.model';

describe('Component Tests', () => {
  describe('MDistributeParamPoint Management Update Component', () => {
    let comp: MDistributeParamPointUpdateComponent;
    let fixture: ComponentFixture<MDistributeParamPointUpdateComponent>;
    let service: MDistributeParamPointService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDistributeParamPointUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDistributeParamPointUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDistributeParamPointUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDistributeParamPointService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDistributeParamPoint(123);
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
        const entity = new MDistributeParamPoint();
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
