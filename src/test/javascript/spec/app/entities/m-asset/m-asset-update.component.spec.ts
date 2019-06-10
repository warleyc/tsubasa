/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAssetUpdateComponent } from 'app/entities/m-asset/m-asset-update.component';
import { MAssetService } from 'app/entities/m-asset/m-asset.service';
import { MAsset } from 'app/shared/model/m-asset.model';

describe('Component Tests', () => {
  describe('MAsset Management Update Component', () => {
    let comp: MAssetUpdateComponent;
    let fixture: ComponentFixture<MAssetUpdateComponent>;
    let service: MAssetService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAssetUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MAssetUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MAssetUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MAssetService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MAsset(123);
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
        const entity = new MAsset();
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
