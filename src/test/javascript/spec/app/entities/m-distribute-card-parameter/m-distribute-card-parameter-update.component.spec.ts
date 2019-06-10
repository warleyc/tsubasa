/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDistributeCardParameterUpdateComponent } from 'app/entities/m-distribute-card-parameter/m-distribute-card-parameter-update.component';
import { MDistributeCardParameterService } from 'app/entities/m-distribute-card-parameter/m-distribute-card-parameter.service';
import { MDistributeCardParameter } from 'app/shared/model/m-distribute-card-parameter.model';

describe('Component Tests', () => {
  describe('MDistributeCardParameter Management Update Component', () => {
    let comp: MDistributeCardParameterUpdateComponent;
    let fixture: ComponentFixture<MDistributeCardParameterUpdateComponent>;
    let service: MDistributeCardParameterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDistributeCardParameterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDistributeCardParameterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDistributeCardParameterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDistributeCardParameterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDistributeCardParameter(123);
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
        const entity = new MDistributeCardParameter();
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
