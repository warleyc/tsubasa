/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLuckDetailComponent } from 'app/entities/m-luck/m-luck-detail.component';
import { MLuck } from 'app/shared/model/m-luck.model';

describe('Component Tests', () => {
  describe('MLuck Management Detail Component', () => {
    let comp: MLuckDetailComponent;
    let fixture: ComponentFixture<MLuckDetailComponent>;
    const route = ({ data: of({ mLuck: new MLuck(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLuckDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLuckDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLuckDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLuck).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
